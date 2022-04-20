function openDialog(dialogId){
    let dialog = document.getElementById(dialogId);
    dialog.showModal();
}

function getStreamParameters() {
    $.ajax({
        url: "/teacherassistant/streams/parameters",
        success: function (data) {
            console.log("start");
            $("#streamInfoHolder").html(data);
            $('#streamInfo').modal('show');
            console.log("end");
        }
    })
}

function getStudentParameters() {
    $.ajax({
        url: "/teacherassistant/students/parameters",
        success: function (data) {
            console.log("start");
            $("#studentInfoHolder").html(data);
            $('#studentInfo').modal('show');
            console.log("end");
        }
    })
}

function getGroupParameters() {
    $.ajax({
        url: "/teacherassistant/groups/parameters",
        success: function (data) {
            console.log("start");
            $("#groupInfoHolder").html(data);
            $('#groupInfo').modal('show');
            console.log("end");
        }
    })
}

function getStreamInfoById(id) {
    $.ajax({
        url: "/teacherassistant/streams/" + id,
        success: function (data) {
            console.log("start");
            $("#streamInfoHolder").html(data);
            $('#streamInfo').modal('show');
            console.log("end");
        }
    })
}

function getGroupInfoById(id) {
    $.ajax({
        url: "/teacherassistant/groups/" + id,
        success: function (data) {
            console.log("start");
            $("#groupInfoHolder").html(data);
            $('#groupInfo').modal('show');
            console.log("end");
        }
    })
}