
jQuery("#startGame").click(function(){
    jQuery.ajax({
        type: "GET",
        cache: false,
        url: '/test',
        data: "",
        success: function (response) {
            jQuery('#container').html(response);
        }
    });
});