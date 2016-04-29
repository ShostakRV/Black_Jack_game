var gameId;
jQuery("#startGame").click(function () {
    jQuery.ajax({
        type: "GET",
        cache: false,
        url: '/game/createGame',
        data: "",
        success: function (response) {
            gameId = response['id'];
            jQuery('#logger').text(response.toString());
            droveCards(response['userCards'], 'userCards');
            droveCards(response['croupierCards'], 'croupierCards');
            console.log(response);
        }
    });
});
function droveCards(arr, divName) {
    jQuery('#' + divName).html('');
    arr.forEach(function (element, index, array) {
        jQuery('#' + divName).html(jQuery('#' + divName).html() + '<canvas width="100" height="150" class="card" id="' + divName + index + '"></canvas>');
    });
    arr.forEach(function (element, index, array) {
        var pair = element.split('_');
        droveCard(divName + index, pair[0], pair[1]);
    });
}
function droveCard(div, par1, par2) {
    var drawingCanvas = document.getElementById(div);
    if (drawingCanvas && drawingCanvas.getContext) {
        var ctx = drawingCanvas.getContext("2d");
        ctx.fillStyle = "#FF0000";
        ctx.font = "30px Arial";
        var symbol;
        if (par1 == 'SPADES') {
            symbol = '♠';
        } else if (par1 == 'HEARTS') {
            symbol = '♥';
        } else if (par1 == 'CLUBS') {
            symbol = '♣';
        } else {
            symbol = '♦';
        }
        ctx.fillText(symbol, 10, 30);
        ctx.fillText(par2, 10, 70);
    }
    console.log(drawingCanvas)
}
