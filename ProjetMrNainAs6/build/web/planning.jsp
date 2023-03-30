<%-- 
    Document   : creerfilm
    Created on : 24 mars 2023, 12:13:33
    Author     : Nekena
--%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="entity.*,java.util.*,Service.*"%>
 <%@page import="entity.*,java.util.*,Service.*"%>
    <!DOCTYPE html>
<html lang="en">

<head>
  <!-- Required meta tags -->
  <meta charset="utf-8">
  
  <title>Skydash Admin</title>
  <!-- plugins:css -->
  <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/vendors/feather/feather.css">
  <link rel="stylesheet" href="<%= request.getContextPath()%>/assets/vendors/ti-icons/css/themify-icons.css">
  <link rel="stylesheet" href="<%= request.getContextPath() %>/vendors/css/vendor.bundle.base.css">
  <!-- endinject -->
  <!-- Plugin css for this page -->
  <link rel="stylesheet" href="<%= request.getContextPath()%>/assets/vendors/datatables.net-bs4/dataTables.bootstrap4.css">
  <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/vendors/ti-icons/css/themify-icons.css">
  <link rel="stylesheet" type="<%= request.getContextPath() %>/text/css" href="<%= request.getContextPath() %>/assets/js/select.dataTables.min.css">
  <!-- End plugin css for this page -->
  <!-- inject:css -->
  <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/vertical-layout-light/style.css">
  <!-- endinject -->
  <link rel="shortcut icon" href="<%= request.getContextPath() %>/assets/images/favicon.png" />
</head>
<body>
  <div class="container-scroller">
          <%
    //String sessionrecherche=(String)request.getAttribute("sessionrecherche");
    //String message=(String)request.getAttribute("message");
    //int nbPage=(Integer)request.getAttribute("nbPage");
    //int numPage=(Integer)request.getAttribute("numPage");
    //int ariana=(Integer)request.getAttribute("ariana");
    //int nombrePage=(Integer)request.getAttribute("nombrePage");
    ArrayList<Indisponibilite> array=(ArrayList<Indisponibilite>)request.getAttribute("indispo");
    
    %>
    <!-- partial:partials/_navbar.html -->
    <nav class="navbar col-lg-12 col-12 p-0 fixed-top d-flex flex-row">
      <div class="text-center navbar-brand-wrapper d-flex align-items-center justify-content-center">
        <a class="navbar-brand brand-logo mr-5" href="<%= request.getContextPath() %>/listfilm"><img src="<%= request.getContextPath() %>/assets/images/logo.svg" class="mr-2" alt="logo"/></a>
        <a class="navbar-brand brand-logo-mini" href="<%= request.getContextPath() %>/listfilm"><img src="<%= request.getContextPath() %>/assets/images/logo-mini.svg" alt="logo"/></a>
      </div>
      <div class="navbar-menu-wrapper d-flex align-items-center justify-content-end">
        <button class="navbar-toggler navbar-toggler align-self-center" type="button" data-toggle="minimize">
          <span class="icon-menu"></span>
        </button>
        <ul class="navbar-nav mr-lg-2">
          <li class="nav-item nav-search d-none d-lg-block">
            <div class="input-group">
              <div class="input-group-prepend hover-cursor" id="navbar-search-icon">
                <span class="input-group-text" id="search">
                  <i class="icon-search"></i>
                </span>
              </div>
              <input type="text" class="form-control" id="navbar-search-input" placeholder="Search now" aria-label="search" aria-describedby="search">
            </div>
          </li>
        </ul>
        <ul class="navbar-nav navbar-nav-right">
          <li class="nav-item dropdown">
            <a class="nav-link count-indicator dropdown-toggle" id="notificationDropdown"  href="<%= request.getContextPath()%>/assets/#" data-toggle="dropdown">
              <i class="icon-bell mx-0"></i>
              <span class="count"></span>
            </a>
            <div class="dropdown-menu dropdown-menu-right navbar-dropdown preview-list" aria-labelledby="notificationDropdown">
              <p class="mb-0 font-weight-normal float-left dropdown-header">Notifications</p>
              <a class="dropdown-item preview-item">
                <div class="preview-thumbnail">
                  <div class="preview-icon bg-success">
                    <i class="ti-info-alt mx-0"></i>
                  </div>
                </div>
                <div class="preview-item-content">
                  <h6 class="preview-subject font-weight-normal">Application Error</h6>
                  <p class="font-weight-light small-text mb-0 text-muted">
                    Just now
                  </p>
                </div>
              </a>
              <a class="dropdown-item preview-item">
                <div class="preview-thumbnail">
                  <div class="preview-icon bg-warning">
                    <i class="ti-settings mx-0"></i>
                  </div>
                </div>
                <div class="preview-item-content">
                  <h6 class="preview-subject font-weight-normal">Settings</h6>
                  <p class="font-weight-light small-text mb-0 text-muted">
                    Private message
                  </p>
                </div>
              </a>
              <a class="dropdown-item preview-item">
                <div class="preview-thumbnail">
                  <div class="preview-icon bg-info">
                    <i class="ti-user mx-0"></i>
                  </div>
                </div>
                <div class="preview-item-content">
                  <h6 class="preview-subject font-weight-normal">New user registration</h6>
                  <p class="font-weight-light small-text mb-0 text-muted">
                    2 days ago
                  </p>
                </div>
              </a>
            </div>
          </li>
          <li class="nav-item nav-profile dropdown">
            <a class="nav-link dropdown-toggle"  href="<%= request.getContextPath()%>/assets/#" data-toggle="dropdown" id="profileDropdown">
              <img src="<%= request.getContextPath() %>/assets/images/faces/face28.jpg" alt="profile"/>
            </a>
            <div class="dropdown-menu dropdown-menu-right navbar-dropdown" aria-labelledby="profileDropdown">
              <a class="dropdown-item">
                <i class="ti-settings text-primary"></i>
                Settings
              </a>
              <a class="dropdown-item">
                <i class="ti-power-off text-primary"></i>
                Logout
              </a>
            </div>
          </li>
          <li class="nav-item nav-settings d-none d-lg-flex">
            <a class="nav-link"  href="<%= request.getContextPath()%>/assets/#">
              <i class="icon-ellipsis"></i>
            </a>
          </li>
        </ul>
        <button class="navbar-toggler navbar-toggler-right d-lg-none align-self-center" type="button" data-toggle="offcanvas">
          <span class="icon-menu"></span>
        </button>
      </div>
    </nav>
    <div class="content-wrapper" >
      <div class="row" style="margin-top: 10%;">
        <div class="col-lg-12 grid-margin stretch-card">
          <div class="card">
            <div class="card-body">
              <h4 class="card-title">Voici la liste des indisponibilites</h4>
              
              <div class="table-responsive">
                <table class="table">
                  <thead>
                    <tr>
                      <th>Heure de debut</th>
                      <th>Plateau</th>
                      <th>Raison</th>
                      
                    </tr>
                  </thead>
                  
                      <%
                     
                       for(int i=0;i<array.size();i++){
                       Indisponibilite fil=(Indisponibilite)array.get(i);%>
                       
                      <tr>
                      <td><%out.println(fil.getHeuredebut()); %></td>
                      <td><% out.println(fil.getIdplateau()); %></td>
                      <%if (fil.getRaison().length()<2) {%>
                              <td> Tournage de la scene n*<% out.println(fil.getRaison()); %></td>
                        <%  }else{ %>
                                 <td> <% out.println(fil.getRaison()); %></td>
                        <%}%>
                      <%
 
                        }%>
                    
                    
                  
                </table>
              </div>
            </div>
          </div>
        </div>
        
      </div>
    </div>
  </div>
  <!-- container-scroller -->

  <!-- plugins:js -->
  <script src="<%= request.getContextPath() %>/assets/vendors/js/vendor.bundle.base.js"></script>
  <!-- endinject -->
  <!-- Plugin js for this page -->
  <script src="<%= request.getContextPath() %>/assets/vendors/chart.js/Chart.min.js"></script>
  <script src="<%= request.getContextPath() %>/assets/vendors/datatables.net/jquery.dataTables.js"></script>
  <script src="<%= request.getContextPath() %>/assets/vendors/datatables.net-bs4/dataTables.bootstrap4.js"></script>
  <script src="<%= request.getContextPath() %>/assets/js/dataTables.select.min.js"></script>

  <!-- End plugin js for this page -->
  <!-- inject:js -->
  <script src="<%= request.getContextPath() %>/assets/js/off-canvas.js"></script>
  <script src="<%= request.getContextPath() %>/assets/js/hoverable-collapse.js"></script>
  <script src="<%= request.getContextPath() %>/assets/js/template.js"></script>
  <script src="<%= request.getContextPath() %>/assets/js/settings.js"></script>
  <script src="<%= request.getContextPath() %>/assets/js/todolist.js"></script>
  <!-- endinject -->
  <!-- Custom js for this page-->
  <script src="<%= request.getContextPath() %>/assets/js/dashboard.js"></script>
  <script src="<%= request.getContextPath() %>/assets/js/Chart.roundedBarCharts.js"></script>
  <!-- End custom js for this page-->
</body>

</html>

