(function () {
    var manageSitePage = "editor",
        manageInventoryPage = "inventory",
        generateReportsPage = "reports";

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
