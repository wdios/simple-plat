<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta charset="utf-8" />
    <meta name="keywords" content="">
    <meta name="description" content="">
    <link rel="shortcut icon" href="${path}/resources/img/favicon.ico">
    <link rel="Bookmark" href="${path}/resources/img/favicon.ico">
    <title>首页 - 管理系统 ${wd-test}</title>

    <meta name="description" content="" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

    <!-- bootstrap & fontawesome -->
    <link rel="stylesheet" href="${path}/resources/js/ace-master/assets/css/bootstrap.min.css" />
    <link rel="stylesheet" href="${path}/resources/js/ace-master/assets/font-awesome/4.5.0/css/font-awesome.min.css" />

    <!-- page specific plugin styles -->

    <!-- text fonts -->
    <link rel="stylesheet" href="${path}/resources/js/ace-master/assets/css/fonts.googleapis.com.css" />

    <!-- ace styles -->
    <link rel="stylesheet" href="${path}/resources/js/ace-master/assets/css/ace.min.css" class="ace-main-stylesheet" id="main-ace-style" />

    <!--[if lte IE 9]>
      <link rel="stylesheet" href="${path}/resources/js/ace-master/assets/css/ace-part2.min.css" class="ace-main-stylesheet" />
    <![endif]-->
    <link rel="stylesheet" href="${path}/resources/js/ace-master/assets/css/ace-skins.min.css" />
    <link rel="stylesheet" href="${path}/resources/js/ace-master/assets/css/ace-rtl.min.css" />

    <!--[if lte IE 9]>
      <link rel="stylesheet" href="${path}/resources/js/ace-master/assets/css/ace-ie.min.css" />
    <![endif]-->

    <!-- inline styles related to this page -->

    <!-- ace settings handler -->
    <script src="${path}/resources/js/ace-master/assets/js/ace-extra.min.js"></script>

    <!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->

    <!--[if lte IE 8]>
    <script src="${path}/resources/js/ace-master/assets/js/html5shiv.min.js"></script>
    <script src="${path}/resources/js/ace-master/assets/js/respond.min.js"></script>
    <![endif]-->
  </head>

  <body class="no-skin">

<jsp:include page="/WEB-INF/views/admin/common/nav.jsp"></jsp:include>

<div class="main-container ace-save-state" id="main-container">
      <script type="text/javascript">
        try{ace.settings.loadState('main-container')}catch(e){}
      </script>

<jsp:include page="/WEB-INF/views/admin/common/leftmenu.jsp"></jsp:include>
<div class="main-content">
  <div class="main-content-inner">
    <div class="breadcrumbs ace-save-state" id="breadcrumbs">
      <ul class="breadcrumb">
        <li>
          <i class="ace-icon fa fa-desktop"></i>
          <a href="#">系统管理</a>
        </li>
        <li class="active">资源管理</li>
      </ul><!-- /.breadcrumb -->

      <div class="nav-search" id="nav-search">
        <form class="form-search">
          <span class="input-icon">
            <input type="text" placeholder="Search ..." class="nav-search-input" id="nav-search-input" autocomplete="off" />
            <i class="ace-icon fa fa-search nav-search-icon"></i>
          </span>
        </form>
      </div><!-- /.nav-search -->
    </div>
    
    <div class="page-content">
      <div class="ace-settings-container" id="ace-settings-container">
        <div class="btn btn-app btn-xs btn-warning ace-settings-btn" id="ace-settings-btn">
          <i class="ace-icon fa fa-cog bigger-130"></i>
        </div>

        <div class="ace-settings-box clearfix" id="ace-settings-box">
          <div class="pull-left width-50">
            <div class="ace-settings-item">
              <div class="pull-left">
                <select id="skin-colorpicker" class="hide">
                  <option data-skin="no-skin" value="#438EB9">#438EB9</option>
                  <option data-skin="skin-1" value="#222A2D">#222A2D</option>
                  <option data-skin="skin-2" value="#C6487E">#C6487E</option>
                  <option data-skin="skin-3" value="#D0D0D0">#D0D0D0</option>
                </select>
              </div>
              <span>&nbsp; Choose Skin</span>
            </div>

            <div class="ace-settings-item">
              <input type="checkbox" class="ace ace-checkbox-2 ace-save-state" id="ace-settings-navbar" autocomplete="off" />
              <label class="lbl" for="ace-settings-navbar"> Fixed Navbar</label>
            </div>

            <div class="ace-settings-item">
              <input type="checkbox" class="ace ace-checkbox-2 ace-save-state" id="ace-settings-sidebar" autocomplete="off" />
              <label class="lbl" for="ace-settings-sidebar"> Fixed Sidebar</label>
            </div>

            <div class="ace-settings-item">
              <input type="checkbox" class="ace ace-checkbox-2 ace-save-state" id="ace-settings-breadcrumbs" autocomplete="off" />
              <label class="lbl" for="ace-settings-breadcrumbs"> Fixed Breadcrumbs</label>
            </div>

            <div class="ace-settings-item">
              <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-rtl" autocomplete="off" />
              <label class="lbl" for="ace-settings-rtl"> Right To Left (rtl)</label>
            </div>

            <div class="ace-settings-item">
              <input type="checkbox" class="ace ace-checkbox-2 ace-save-state" id="ace-settings-add-container" autocomplete="off" />
              <label class="lbl" for="ace-settings-add-container">
                Inside
                <b>.container</b>
              </label>
            </div>
          </div><!-- /.pull-left -->

          <div class="pull-left width-50">
            <div class="ace-settings-item">
              <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-hover" autocomplete="off" />
              <label class="lbl" for="ace-settings-hover"> Submenu on Hover</label>
            </div>

            <div class="ace-settings-item">
              <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-compact" autocomplete="off" />
              <label class="lbl" for="ace-settings-compact"> Compact Sidebar</label>
            </div>

            <div class="ace-settings-item">
              <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-highlight" autocomplete="off" />
              <label class="lbl" for="ace-settings-highlight"> Alt. Active Item</label>
            </div>
          </div><!-- /.pull-left -->
        </div><!-- /.ace-settings-box -->
      </div><!-- /.ace-settings-container -->

      <div class="page-header">
        <h1>
          系统管理
          <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            资源管理
          </small>
        </h1>
      </div><!-- /.page-header -->

      <div class="row">
        <div class="col-xs-12">
        
        </div>
      </div>
    </div>
    
  </div>
</div>
<jsp:include page="/WEB-INF/views/admin/common/footer.jsp"></jsp:include>

      <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
        <i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
      </a>
    </div><!-- /.main-container -->

    <!-- basic scripts -->

    <!--[if !IE]> -->
    <script src="${path}/resources/js/ace-master/assets/js/jquery-2.1.4.min.js"></script>

    <!-- <![endif]-->

    <!--[if IE]>
<script src="${path}/resources/js/ace-master/assets/js/jquery-1.11.3.min.js"></script>
<![endif]-->
    <script type="text/javascript">
      if('ontouchstart' in document.documentElement) document.write("<script src='${path}/resources/js/ace-master/assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
    </script>
    <script src="${path}/resources/js/ace-master/assets/js/bootstrap.min.js"></script>

    <!-- page specific plugin scripts -->

    <!--[if lte IE 8]>
      <script src="assets/js/excanvas.min.js"></script>
    <![endif]-->
    <script src="${path}/resources/js/ace-master/assets/js/jquery-ui.custom.min.js"></script>
    <script src="${path}/resources/js/ace-master/assets/js/jquery.ui.touch-punch.min.js"></script>
    <script src="${path}/resources/js/ace-master/assets/js/jquery.easypiechart.min.js"></script>
    <script src="${path}/resources/js/ace-master/assets/js/jquery.sparkline.index.min.js"></script>
    <script src="${path}/resources/js/ace-master/assets/js/jquery.flot.min.js"></script>
    <script src="${path}/resources/js/ace-master/assets/js/jquery.flot.pie.min.js"></script>
    <script src="${path}/resources/js/ace-master/assets/js/jquery.flot.resize.min.js"></script>

    <!-- ace scripts -->
    <script src="${path}/resources/js/ace-master/assets/js/ace-elements.min.js"></script>
    <script src="${path}/resources/js/ace-master/assets/js/ace.min.js"></script>
    
    <script src="${path}/resources/js/utils.js"></script>

    <!-- inline scripts related to this page -->
    <script type="text/javascript">
      var treeMenuHtml = 'blank';
      function initLeftMenu() {
        var leftMenuData = {};
        $.post("${path}/admin/sysMenu/all", {}, function(result) {
          if (isNotBlank(result)) {
            leftMenuData = JSON.parse(result); //由JSON字符串转换为JSON对象
            treeMenuHtml = new treeMenu(leftMenuData).init(0);
            $('#left-menu').append(treeMenuHtml);
            // alert(treeMenuHtml);
          }
        });
        // alert($('#menu-test'));
      }
      
      jQuery(function($) {
        initLeftMenu();
        
      })
    </script>
  </body>
</html>