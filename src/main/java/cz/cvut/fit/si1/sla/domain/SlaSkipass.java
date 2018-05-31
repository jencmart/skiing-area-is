package cz.cvut.fit.si1.sla.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "sla_skipass")
public class SlaSkipass {

    @Id
    @Column(name = "id_skipass")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSkipass;

    @Column(name = "price")
    private Long price;

    @Column(name = "number_of_days")
    private Long numberOfDays;

    @OneToMany(mappedBy = "skipass", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SlaOrderSkipassArticle> articles;

    public Long getIdSkipass() {
        return idSkipass;
    }

    public void setIdSkipass(Long id_skipass) {
        this.idSkipass = id_skipass;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(Long numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    public List<SlaOrderSkipassArticle> getArticles() {
        return articles;
    }

    public void setArticles(List<SlaOrderSkipassArticle> articles) {
        this.articles = articles;
    }

    public boolean isNew() {
        return this.idSkipass == null;
    }
}
