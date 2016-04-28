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
            jQuery('#userCards').text(response['userCards']);
            jQuery('#coupeCards').text(response['croupierCards']);
        }
    });
});

jQuery("#hitCard").click(function () {
    jQuery.ajax({
        type: "GET",
        cache: false,
        url: '/game/hitCard',
        data: {'colors[]': ["Red", "Green", "Blue"]},
        success: function (response) {
            gameId = response['id'];
            jQuery('#logger').text(response.toString());
            jQuery('#userCards').text(response['userCards']);
            jQuery('#coupeCards').text(response['croupierCards']);
        }
    });
});

