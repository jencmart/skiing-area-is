$(".rent").click(function () {

    let idOrder = $(this).data("order");
    let idArticle = $(this).data("article");

    $("input[name='orderid']").val(idOrder);
    $("input[name='articleid']").val(idArticle);

    $("#secondary").hide();
    $("#myModal").modal('show');
});


$(".returnCard").click(function () {
    $("#returnRfid").modal('show');
});

$(".find").click(function () {
    $("#find").modal('show');
});

$(".return").click(function () {

    let idOrder = $(this).data("order");
    let idArticle = $(this).data("article");
    let token = $("input[name='_csrf']").val();

    $.ajax({
        type: 'POST',
        url: '/management/order/' + idOrder + '/return/' + idArticle,
        data: {
            '_csrf': token,
            success(data) {
                window.location.replace("/management/order/" + idOrder);
            }
        }
    });
});


$("#target").submit(function (event) {

    let rfid = $("#rfid").val();
    let token = $("input[name='_csrf']").val();

    $.ajax({
        type: 'GET',
        url: '/management/chipcard/ajaxFind/' + rfid,
        data: {
            '_csrf': token
        },
        success: function (chipcard) { //todo error nefunguje mrdka
            if (chipcard.length === 0) {
                alert("Karta neni v systemu evidovana!");
            }
            else if (chipcard["cardRented"] === "true") {
                alert("Karta je evidovana jako zapujcena!\nPouzijte prosim jinou kartu!");
            }
            else {
                let chipcardId = chipcard["idChipCard"];
                let chipcardPrice = chipcard["depositPrice"];

                $("input[name='deposit']").val(chipcardPrice + " Kc");
                $("input[name='idchipcard']").val(chipcardId);

                $("#secondary").fadeIn();
            }
        }
    });

    event.preventDefault();
});

$("#secondary").submit(function (event) {

    let token = $("input[name='_csrf']").val();
    let orderId = $("input[name='orderid']").val();
    let articleId = $("input[name='articleid']").val();
    let chipcardId = $("input[name='idchipcard']").val();

    $.ajax({
        type: 'POST',
        url: '/management/order/' + orderId + '/rent',
        data: {
            '_csrf': token,
            'orderId': orderId,
            'articleId': articleId,
            'chipCardId': chipcardId
        }, success(data) {
            window.location.replace("/management/order/" + orderId);
        }
    });

});


//****************************************************************
//*************** INTERNAL SHOPPING CART *************************
//****************************************************************


$("#additems").click(function () {

    $("#addArticlesModal").modal('show');
});


$.fn.exists = function () {
    return this.length !== 0;
};


function updateShoppingCartTable(myId, price, numdays) {

    let r = $("tbody#tableOrders").find("tr#" + myId);

    if (r.exists()) {
        let input = $("input[name='quant[" + myId + "]']");
        let currentVal = parseInt(input.val());
        input.val(currentVal + 1).change();
        changeCount(myId, (currentVal + 1));
    }
    else {
        $("tbody#tableOrders:last-child").append(
            "<tr  id=\"" + myId + "\"  >" +
            "<td>Skipass</td><td>" + numdays + "</td><td ><span class=\"item-price\">" + price + "</span> Kc</td>" +
            "<td>" +
            "<div class=\"input-group col-sm-5\">" +
            "<span class=\"input-group-btn btn-group-xs\">" +
            "<button type=\"button\" class=\"btn btn-danger btn-number btn-xs\"  data-type=\"minus\" data-field=\"quant[" + myId + "]\">" +
            "<span class=\"fa fa-minus fa-xs\"></span></button></span>" +
            "<input type=\"text\" disabled=\"disabled\" name=\"quant[" + myId + "]\" class=\"form-control input-number\" value=\"1\" min=\"1\" max=\"100\">" +
            "<span class=\"input-group-btn\">" +
            " <button type=\"button\" class=\"btn btn-success btn-number btn-xs\"   data-type=\"plus\"  data-field=\"quant[" + myId + "]\"><span class=\"fa fa-plus fa-xs\"></span></button></span></div>" +
            "</td>" +
            "<td><a class=\"btn  btn-xs btn-danger btn-delete-item\" href=\"#\" ><i class=\"fa fa-trash fa-xs\"></i> Odstranit !</a></td></tr>");
        updateTotalPrice();
    }
}


$(".skipassBtn").click(function () { //todo edit adresa
    let myId = $(this).data("idskipass");
    let price = $(this).data("price");
    let numdays = $(this).data("numdays");

    let token = $("input[name='_csrf']").val();

    $.ajax({
        type: 'POST',
        url: '/management/order/new/' + myId + '/add',
        data: {
            '_csrf': token
        },
        success: function (cntItems) {

            updateShoppingCartTable(myId, price, numdays);
        }
    });


});

function updateTotalPrice() {
    let price = 0;

    $(".item-price").each(function () {
        let line = $(this).closest('tr');
        if (line.is(":visible"))
            price += parseFloat($(this)[0].innerText);
    });

    $("#total-price")[0].innerText = price;
}


$('#tableOrders').on('click', '.btn-delete-item', function () {


    let value = parseInt($(this).closest("tr").find(".item-price")[0].innerText);
    let id = $(this).closest("tr").attr("id");
    $("#" + id).fadeOut().remove();

    let token = $("input[name='_csrf']").val();

    $.ajax({
        type: 'POST',
        url: '/management/order/new/' + id + '/remove',
        data: {
            '_csrf': token
        },
        success: function (cnt) {
            updateShoppingCardCount(cnt);
            let mySpan = $("#total-price")[0];

            mySpan.innerText = parseInt(mySpan.innerText) - value;
        }
    });


});


function changeCount(itemId, cnt) {

    let token = $("input[name='_csrf']").val();

    $.ajax({
        type: 'POST',
        url: '/management/order/new/' + itemId + '/setcount/' + cnt,
        data: {
            '_csrf': token
        },
        success: function (resp) {
            $("tr[id='" + itemId + "']").find("span.item-price")[0].innerText = (cnt * resp['price']);
            updateTotalPrice();
        }
    });
}


//plugin bootstrap minus and plus
//http://jsfiddle.net/laelitenetwork/puJ6G/
$('#tableOrders').on('click', '.btn-number', function (e) {
    e.preventDefault();

    fieldName = $(this).attr('data-field');
    type = $(this).attr('data-type');
    let input = $("input[name='" + fieldName + "']");
    let currentVal = parseInt(input.val());
    if (!isNaN(currentVal)) {
        let id = $(this).closest("tr").attr("id");

        if (type === 'minus') {
            if (currentVal > input.attr('min')) {
                input.val(currentVal - 1).change();
                changeCount(id, (currentVal - 1));
            }
            if (parseInt(input.val()) === input.attr('min')) {
                $(this).attr('disabled', true);
            }
        } else if (type === 'plus') {

            if (currentVal < input.attr('max')) {
                input.val(currentVal + 1).change();
                changeCount(id, (currentVal + 1));
            }
            if (parseInt(input.val()) === input.attr('max')) {
                $(this).attr('disabled', true);
            }
        }
    } else {
        input.val(0);
    }
});
$('.input-number').focusin(function () {
    $(this).data('oldValue', $(this).val());
});
$('.input-number').change(function () {

    minValue = parseInt($(this).attr('min'));
    maxValue = parseInt($(this).attr('max'));
    valueCurrent = parseInt($(this).val());

    name = $(this).attr('name');
    if (valueCurrent >= minValue) {
        $(".btn-number[data-type='minus'][data-field='" + name + "']").removeAttr('disabled')
    } else {
        alert('Sorry, the minimum value was reached');
        $(this).val($(this).data('oldValue'));
    }
    if (valueCurrent <= maxValue) {
        $(".btn-number[data-type='plus'][data-field='" + name + "']").removeAttr('disabled')
    } else {
        alert('Sorry, the maximum value was reached');
        $(this).val($(this).data('oldValue'));
    }

});
$(".input-number").keydown(function (e) {
    // Allow: backspace, delete, tab, escape, enter and .
    if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 190]) !== -1 ||
        // Allow: Ctrl+A
        (e.keyCode == 65 && e.ctrlKey === true) ||
        // Allow: home, end, left, right
        (e.keyCode >= 35 && e.keyCode <= 39)) {
        // let it happen, don't do anything
        return;
    }
    // Ensure that it is a number and stop the keypress
    if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
        e.preventDefault();
    }
});
