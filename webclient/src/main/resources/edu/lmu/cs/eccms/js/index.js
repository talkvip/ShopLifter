(function () {
    var manageSitePage = "AboutPage",
        manageInventoryPage = "",
        generateReportsPage = "";

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
