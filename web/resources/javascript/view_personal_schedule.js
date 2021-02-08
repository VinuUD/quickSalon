var spID=1;

$(document).ready(function() {
    //GET All Appointment List for service provider bt spID
    $.get("serviceList", function (responseJson) {
        var $select = $("#servicedropdownlist");
        $.each(responseJson, function (index, service) {
            $("<option>").val(index).text(service.serviceName).appendTo($select);
            serviceDetails.push({
                serviceId: service.serviceID,
                service: service.serviceName,
                time: new Time(Math.floor(service.timeTaken / 60), service.timeTaken % 60)
            })
        });
    });

});
