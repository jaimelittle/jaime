//网站的JS主文件
;(function ($) {

    'use strict'
    /*判断是否移动*/
    var isMobile = {
        Android: function () {
            return navigator.userAgent.match(/Android/i);
        },
        BlackBerry: function () {
            return navigator.userAgent.match(/BlackBerry/i);
        },
        iOS: function () {
            return navigator.userAgent.match(/iPhone|iPad|iPod/i);
        },
        Opera: function () {
            return navigator.userAgent.match(/Opera Mini/i);
        },
        Windows: function () {
            return navigator.userAgent.match(/IEMobile/i);
        },
        any: function () {
            return (isMobile.Android() || isMobile.BlackBerry() || isMobile.iOS() || isMobile.Opera() || isMobile.Windows());
        }
    };

    //菜单 在不同设备上的显示
    var responsiveMenu = function () {
        var menuType = 'desktop';//默认PC机模式

        $(window).on('load resize', function () {
            var currMenuType = 'desktop';

            //屏幕小于991为移动模式
            if (matchMedia('only screen and (max-width: 991px)').matches) {
                currMenuType = 'mobile';
            }

            if (currMenuType !== menuType) {
                menuType = currMenuType;

                if (currMenuType === 'mobile') {


                    //移动端显示

                    //var $mobileMenu = $('#mainnav').attr('id', 'menu_right').hide();
                   // var hasChildMenu = $('menu_right').find('li:has(ul)');

                    var hasChildMenu = $('#mainnav').find('a');//移动的子菜单
                    var hasChildMenu_right = $('#menu_right').find('a');//移动的子菜单


                    $('#mainnav').hide();//隐藏菜单
                     $('#menu_right').hide();//隐藏菜单
                     $('#menu_mobi').show();//显示移动菜单


                    $('#menu_mobi').children('li').children('dl').append(hasChildMenu);
                    $('#menu_mobi').children('li').children('dl').append(hasChildMenu_right);



                    /*手机顶部菜单 */
                   // hasChildMenu.children('ul').hide();


                } else {
                    $('#menu_mobi').hide();
                }
            }
        });


    }



    // Dom Ready
    //加载功能
    $(function () {



        responsiveMenu();//菜单 在不同设备上的显示

    });

})(jQuery);