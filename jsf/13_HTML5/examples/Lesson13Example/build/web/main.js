
window.onload=showProgress();
var loaded=0;
            function showProgress(loaded)
            {     
             
                var bar = document.getElementById('bar');
                var status = document.getElementById('status');
                          
                
                status.innerHTML=loaded+"%";
                bar.value=loaded;
                loaded++;
                var sim=setTimeout("showProgress("+loaded+")",100);
            
            
                if(loaded==100)  
                {
                    status.innerHTML="100%";
                    bar.value=100;
                    clearTimeout(sim);
                   
       
                  
                      
                }
  
            }
    
             
     
         