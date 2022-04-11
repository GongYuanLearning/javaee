<%--
  Created by IntelliJ IDEA.
  User: lzj
  Date: 2021/11/29
  Time: 14:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ include file="common/header.jsp" %>
<div class="breadcrumb">
    <a href="#">全部分类</a>
    <span>></span>
    <a href="#">新鲜水果</a>
    <span>></span>
    <a href="#">商品详情</a>
</div>

<div class="goods_detail_con clearfix">
    <div class="goods_detail_pic fl"><img src="${ctx}/product/image?path=${product.imagePath}" with="350" height="350"></div>

    <div class="goods_detail_list fr">
        <h3>${product.name}</h3>
        <p>${product.description}</p>
        <div class="prize_bar">
            <span class="show_pirze">¥<em>${product.price}</em></span>
            <span class="show_unit">单  位：${product.unit}</span>
        </div>
        <div class="goods_num clearfix">
            <div class="num_name fl">数 量：</div>
            <div class="num_add fl">
                <input type="text" class="num_show fl" value="1">
                <a href="javascript:;" class="add fr">+</a>
                <a href="javascript:;" class="minus disabled fr">-</a>
            </div>
        </div>
        <div class="total">总价：<em>${product.price}元</em></div>
        <div class="operate_btn">
            <a href="javascript:;" class="buy_btn">立即购买</a>
            <a href="javascript:;" class="add_cart" id="add_cart">加入购物车</a>
        </div>
    </div>
</div>

<div class="main_wrap clearfix">
    <div class="l_wrap fl clearfix">
        <div class="new_goods">
            <h3>新品推荐</h3>
        </div>
        <ul id="recommendation"><ul>
    </div>

    <div class="r_wrap fr clearfix">
        <ul class="detail_tab clearfix">
            <li class="active">商品介绍</li>
            <li>评论</li>
        </ul>

        <div class="tab_content">
            <dl>
                <dt>商品详情：</dt>
                <dd>${product.detail}</dd>
            </dl>
        </div>

    </div>
</div>

<%@ include file="common/footer.jsp" %>

<div class="add_jump"></div>

<script type="text/javascript" src="${ctx}/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
    $(document).ready(() => {
        //$('#recommendation').load("${ctx}/recommend.jsp");
        $.get("${ctx}/recommend.jsp", function(data) {
            $('#recommendation').append(data);
        });
        $('.add').click(() => {
            let count = Number.parseInt($('.num_show').val(), 10);
            $('.num_show').val(count +1);
            if(count + 1 > 1) {
                $('.minus').removeClass('disabled')
            }
        });
        $('.minus').click((function () {
            if($(this).hasClass('disabled')) {
                return;
            }
            let count = Number.parseInt($('.num_show').val(), 10);
            $('.num_show').val(count - 1);
            if(count - 1 == 1) {
                $(this).addClass('disabled')
            }
        }));
    });

    var $add_x = $('#add_cart').offset().top;
    var $add_y = $('#add_cart').offset().left;

    var $to_x = $('#show_count').offset().top;
    var $to_y = $('#show_count').offset().left;

    $(".add_jump").css({'left':$add_y+80,'top':$add_x+10,'display':'block'})

    const productId = ${product.id};
    const addToCartUrl = '${ctx}/addToCart';

    function addToCartCallback(data) {
        if(data.success && !data.exist) {
            $(".add_jump").stop().animate({
                    'left': $to_y+7,
                    'top': $to_x+7},
                "fast", function() {
                    $(".add_jump").fadeOut('fast',function(){
                        $('#show_count').html(2);
                    });
                });
            return;
        }
        if(data.error) {
            alert(data.error);
        }
    }
    $('#add_cart').click(function(){
        $.ajax({
            url: addToCartUrl,
            type: 'POST',
            data: {id: productId, count: $(".num_show").val()},
            contentType: 'application/x-www-form-urlencoded',
            dataType: 'json',
            success: addToCartCallback
        })
        // $.post(addToCartUrl, {id: productId, count: $(".num_show").val()},
        //     addToCartCallback, "text");

    })
</script>

</body>
</html>
