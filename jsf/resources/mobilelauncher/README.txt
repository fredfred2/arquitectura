Create a copy of the Chrome shortcut and name it "Mobile Chrome".
Right-click the "Mobile Chrome" shortcut and click on Properties.
In the Properties window append to the existing Target the text:
%CHROME_OPTS%
Ensure that you add a space after the closing qotes in the Target text box and then enter %CHROME_OPTS%
Click OK.

To make the Mobile Chrome shortcut function, please create a new environmental variable named CHROME_OPTS with a value of:
--no-default-browser-check --user-agent="Mozilla/5.0 (iPhone; U; CPU iPhone OS 4_3_3 like Mac OS X; en-us) AppleWebKit/533.17.9 (KHTML, like Gecko) Version/5.0.2 Mobile/8J2 Safari/6533.18.5" file:///D:/labs/resources/mobilelauncher/mobileloader.html