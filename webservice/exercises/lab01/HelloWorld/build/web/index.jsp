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
                $('#ajaxPostLink').click(function(){
                    postString();
                });
            });

            function readString() {
                console.log('reading string');
                $.ajax({
					cache: false,
                    url: 'resources/helloWorld',
                    //headers: {
                    //    Accept : "text/plain; charset=utf-8",
                    //    "Content-Type": "text/plain; charset=utf-8"
                    //},
                    dataType: "text",
                    accepts: {
                        text: "text/plain"
                    },
                    success: function(data, textStatus, jqXHR){
                        alert('Read: ' + data);
                    },
                    error: function(jqXHR, textStatus, errorThrown){
                        alert('GET error: ' + textStatus + ',' + errorThrown);
                    }
                });
            }

            function postString() {
                console.log('changing string');
                $.ajax({
                    type: 'PUT',
                    contentType: 'text/plain',
                    processData: false,
                    url: 'resources/helloWorld',
                    data: $('#theinput').val(),
                    success: function(data, textStatus, jqXHR){
                        $('#theinput').val('')
                        alert('Text changed successfully');
                    },
                    error: function(jqXHR, textStatus, errorThrown){
                        $('#theinput').val('')
                        alert('POST error: ' + textStatus + ',' + errorThrown);
                    }
                });
            }
        </script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>REST Client</title>
    </head>
    <body>
        <a id="ajaxGetLink" href="javascript:void(0)">Get</a><br/><br/>
        <input id="theinput" type="text"/>
        <a id="ajaxPostLink" href="javascript:void(0)">Send</a>
    </body>
</html>
