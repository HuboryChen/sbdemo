require(['js/main.js'],function (main) {
    requirejs(['jquery', 'layer', 'bootstrap','chosen'], function ($,layer) {

       /* /!*<![CDATA[*!/
        $(function () {
            var optionstring = "";
            $.ajax({
                url: '/roles',
                type: 'get',
                dataType: "json",
                success:function (data) {
                    var roles = /!*[[${user.roles}]]*!/null;
                    var array = new Array();
                    $.each(roles,function (index,value,arr) {
                        array[index] = value.id;
                    });
                    $.each(data, function (key, value) {
                        optionstring += "<label class='checkbox-line'><input type='checkbox' name='roles["+key+"].id' value='"+value.id+"'  "+(array.indexOf(value.id) != -1? 'checked':'')+">"+value.name+"</label>&nbsp;&nbsp;";
                    });

                    $("#roles").append(optionstring);
                }
            })
        })
        /!*]]>*!/*/


        $("#btn_save").click(function () {
            var url = $("#user_form").attr("action");
            var param = $("#user_form").serialize();
            $.ajax({
                type: "post",
                url: url,
                data: param,
                complete: function () {
                    parent.window.location.reload();
                    parent.layer.closeAll();
                }
            })
        })

    })
})