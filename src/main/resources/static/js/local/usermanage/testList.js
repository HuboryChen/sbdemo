$(function () {
    $("#btn_add_data").click(function () {$.get("/testRedis/save"); });

    $("#btn_edit_data").click(function () {$.post("/testRedis/modify",{key:"test",value:"yyyyyyyyyyyyyyyyyyyyyyyyy"}); });

    $("#btn_delete_data").click(function () {$.post("/testRedis/delete",{key:"test"}); });


    });



