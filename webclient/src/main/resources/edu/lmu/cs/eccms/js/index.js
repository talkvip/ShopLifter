(function () {
    var manageSitePage = "Editor",
        manageInventoryPage = "Inventory",
        generateReportsPage = "Reports",
        testData = [
                {
                    name:"Water Bottle",
                    sku:23,
                    description:"Plastic",
                    price:3.4,
                    dimension:{},
                    itemHistory:{}
                },{
                    name:"Paper Plates",
                    sku:42,
                    description:"Paper",
                    price:5.2,
                    dimension:{},
                    itemHistory:{}
                }
            ];

    $("#manageSiteLink").bind('mouseup', function () {
        window.location.href = manageSitePage;
    });
    $("#manageInventoryLink").bind('mouseup', function () {
        window.location.href = manageInventoryPage;
    });
    $("#generateReportsLink").bind('mouseup', function () {
        window.location.href = generateReportsPage;
    });




})();
