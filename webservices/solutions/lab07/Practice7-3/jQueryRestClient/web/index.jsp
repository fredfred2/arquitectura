<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <script type="text/javascript" src="jquery.js"></script>          
        <script type="text/javascript">                                         
            // we will add our javascript code here
			if (!window.console) {
                window.console = {
                    log: function(){}
                };
            }
            $(document).ready(function() {
                // do stuff when DOM is ready
                $('#ajaxGetLink').click(function(){
                    readString();
                });
            });
            
            function readString() {
                console.log('reading server status');
                $.ajax({
					cache: false,
                    url: '/management/tenant-monitoring/servers/' + $('#theinput').val(),
                    dataType: "json",
                    accepts: {
                        text: "application/json"
                    },
                    success: function(data, textStatus, jqXHR){
                        alert('Read ' + data);
                        console.log('Read: ' + data);
                    },
                    error: function(jqXHR, textStatus, errorThrown){
                        alert('GET error: ' + textStatus + ',' + errorThrown);
                    }
                });
            }
        </script>                                   
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>REST Client</title>
    </head>
    <body>
        Display Current Heap Size for Server<br/>
        <a id="ajaxGetLink" href="javascript:void(0)">Get</a><br/><br/>
        Server Name: <input id="theinput" type="text"/>
    </body>
</html>
