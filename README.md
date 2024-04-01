Welcome to the Azure function kata.

This kata explains how to run Azure functions locally.

Please follow the following steps:

1) Download the Azure function core tools.

    These tools enable running the Functions locally.

    link is : https://learn.microsoft.com/en-us/azure/azure-functions/functions-run-local?tabs=windows%2Cisolated-process%2Cnode-v4%2Cpython-v2%2Chttp-trigger%2Ccontainer-apps&pivots=programming-language-java#install-the-azure-functions-core-tools

    This should be installed into : "C:\Program Files\Microsoft\Azure Functions Core Tools"

    The exe itself will be located at : "C:\Program Files\Microsoft\Azure Functions Core Tools\func.exe"

2) Download the Azure plugin for IntelliJ:

    Go to  File -> Settings -> Plugins

    In the marketplcae choose and install the "Azure Toolkit for IntelliJ"

3) Download the source code in this project by running the following commands , make sure you're not in AT&T VPN, otherwise it will not clone:

   git clone https://github.com/eranlevi1976/azure-functions-kata.git
   
   git fetch origin skeleton
   
   git checkout skeleton

   Then open a new project using IntelliJ, and use this source code.

4) Ask me for updated local.settings.json, which contains all the relevant connection strings
