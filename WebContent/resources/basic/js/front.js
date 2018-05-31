
function updateShoppingCardCount(cntItems) {
    let cart = $("#shoppingCartCount")[0];
    cart.innerText= cntItems.toString() ;
}


$(".skipassBtn").click(function() {
    let id = $(this).data("idskipass");

    let token = $("input[name='_csrf']").val();

    $.ajax({
        type: 'POST',
        url: '/shoppingcart/' + id + '/add',
        data: {
            '_csrf' : token
        },
        success: function (cntItems) {
           updateShoppingCardCount(cntItems);
        }
    });
});

function updateTotalPrice(){
    let price = 0;

    $(".item-price").each(function() {
        let line = $(this).closest('tr');
        if(line.is(":visible"))
            price +=  parseFloat( $( this )[0].innerText) ;
    });

    $("#total-price")[0].innerText = price;
}

$(".btn-delete-item").on("click",function () {


    let value = parseInt($(this).closest("tr").find(".item-price")[0].innerText) ;
    let id = $(this).closest("tr").attr("id");
    $("#"+id).fadeOut();

    let token = $("input[name='_csrf']").val();

    $.ajax({
        type: 'POST',
        url: '/shoppingcart/' + id + '/remove',
        data: {
            '_csrf' : token
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
        url: '/shoppingcart/' + itemId + '/setcount/' + cnt,
        data: {
            '_csrf' : token
        },
        success: function (resp) {
            updateShoppingCardCount(resp['cnt']);
            $("tr[id='"+ itemId + "']").find("span.item-price")[0].innerText = (resp['cnt'] * resp['price']) ;
            updateTotalPrice();
        }
    });
}



//plugin bootstrap minus and plus
//http://jsfiddle.net/laelitenetwork/puJ6G/
$('.btn-number').click(function(e){
    e.preventDefault();

    fieldName = $(this).attr('data-field');
    type      = $(this).attr('data-type');
    let input = $("input[name='"+fieldName+"']");
    let currentVal = parseInt(input.val());
    if (!isNaN(currentVal)) {
        let id = $(this).closest("tr").attr("id");

        if(type === 'minus') {
            if(currentVal > input.attr('min')) {
                input.val(currentVal - 1).change();
                changeCount(id, (currentVal-1));
            }
            if(parseInt(input.val()) === input.attr('min')) {
                $(this).attr('disabled', true);
            }
        } else if(type === 'plus') {

            if(currentVal < input.attr('max')) {
                input.val(currentVal + 1).change();
                changeCount(id, (currentVal+1));
            }
            if(parseInt(input.val()) === input.attr('max')) {
                $(this).attr('disabled', true);
            }
        }
    } else {
        input.val(0);
    }
});
$('.input-number').focusin(function(){
    $(this).data('oldValue', $(this).val());
});
$('.input-number').change(function() {

    minValue =  parseInt($(this).attr('min'));
    maxValue =  parseInt($(this).attr('max'));
    valueCurrent = parseInt($(this).val());

    name = $(this).attr('name');
    if(valueCurrent >= minValue) {
        $(".btn-number[data-type='minus'][data-field='"+name+"']").removeAttr('disabled')
    } else {
        alert('Sorry, the minimum value was reached');
        $(this).val($(this).data('oldValue'));
    }
    if(valueCurrent <= maxValue) {
        $(".btn-number[data-type='plus'][data-field='"+name+"']").removeAttr('disabled')
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
