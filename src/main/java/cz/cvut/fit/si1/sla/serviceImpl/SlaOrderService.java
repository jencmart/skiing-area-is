package cz.cvut.fit.si1.sla.serviceImpl;

import cz.cvut.fit.si1.sla.dao.SlaChipCardDao;
import cz.cvut.fit.si1.sla.dao.SlaOrderDao;
import cz.cvut.fit.si1.sla.dao.SlaOrderSkipassArticleDao;
import cz.cvut.fit.si1.sla.dao.SlaRentDao;
import cz.cvut.fit.si1.sla.domain.*;
import cz.cvut.fit.si1.sla.dto.RentDto;
import cz.cvut.fit.si1.sla.model.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class SlaOrderService {

    @Autowired
    SlaOrderDao orderDao;

    @Autowired
    SlaOrderSkipassArticleDao articleDao;

    @Autowired
    SlaRentDao rentDao;

    @Autowired
    SlaChipCardDao chipCardDao;


    public List<SlaOrder> getAllList() {
        return orderDao.getAllOrders();
    }


    public SlaOrder findById(Integer id) {
        return orderDao.getOneOrder(id.longValue());
    }

    public SlaOrder createOrderFromCartItems(SlaCustomer customer, List<CartItem> cartItems) {

        //create new order
        SlaOrder order = new SlaOrder();

        if (customer != null)
            order.setCustomer(customer);

        order.setCreated(new Timestamp(System.currentTimeMillis()));
        orderDao.create(order);

        // generate articles
        List<SlaOrderSkipassArticle> articles = createArticles(order, cartItems);

        // add articles to order
        order.setArticles(articles);
        orderDao.update(order);

        return order;
    }

    private List<SlaOrderSkipassArticle> createArticles(SlaOrder order, List<CartItem> cartItems) {

        List<SlaOrderSkipassArticle> articles = new ArrayList<>();

        for (CartItem item : cartItems) {
            SlaSkipass skipass = item.getSkipass();
            for (int i = 0; i < item.getCount(); ++i) {
                SlaOrderSkipassArticle article = new SlaOrderSkipassArticle();
                article.setOrder(order);
                article.setSkipass(skipass);
                articleDao.create(article);
                articles.add(article);
            }
        }

        return articles;
    }

    public List<SlaOrderSkipassArticle> getArticles(SlaOrder slaOrder) {


        return slaOrder.getArticles();
    }


    public List<SlaOrderSkipassArticle> getAllArticlesList() {
        return articleDao.getAllOrderSkipassArticles();
    }

    public void rent(RentDto rentDto) {

        SlaRent rent = new SlaRent();
        SlaOrderSkipassArticle article = articleDao.getOneOrderSkipassArticle(((Integer) (rentDto.getArticleId())).longValue());

        SlaChipCard chipCard = chipCardDao.getOneChipCard(((Integer) (rentDto.getChipCardId())).longValue());

        Timestamp now = new Timestamp(System.currentTimeMillis());
        Long miliseconds = (long) (article.getSkipass().getNumberOfDays().intValue() * 24 * 3600);
        SlaEmployee employee = ((SlaUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmployee();

        rent.setArticle(article);
        rent.setChipCard(chipCard);
        rent.setDepositPaid(true);
        rent.setEmployee(employee);
        rent.setFromDate(now);

        rent.setToDate(new Timestamp(now.getTime() + miliseconds));
        rentDao.create(rent);

        article.setCardRented(true);
        articleDao.update(article);

        chipCard.setCardRented(true);
        chipCardDao.update(chipCard);

    }

    public SlaOrderSkipassArticle getArticle(int idArticle) {
        return articleDao.getOneOrderSkipassArticle((long) idArticle);
    }

    public void updateRent(SlaRent rent) {
        rentDao.update(rent);
    }

    public void updateArticle(SlaOrderSkipassArticle article) {
        articleDao.update(article);
    }

    public List<SlaOrder> getOrdersOfCustomer(SlaCustomer customer) {

        return orderDao.getOrdersOfCustomer(customer.getId_customer());
    }

    public void updateOrder(SlaOrder order) {
        orderDao.update(order);
    }

    public int calculatePrice(SlaOrder slaOrder) {
        int price = 0;
        for (SlaOrderSkipassArticle article : slaOrder.getArticles()) {
            price += article.getSkipass().getPrice();
        }

        return price;
    }
}
