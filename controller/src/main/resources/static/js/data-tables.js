const currentUrlParamsStr = window.location.search;
const currentUrlParams = new URLSearchParams(currentUrlParamsStr);

let prStUrl = /*[[@{/registration/present-students}]]*/ 'registration/present-students';
let abStUrl = /*[[@{/registration/absent-students}]]*/ 'registration/absent-students';
const lessonId = currentUrlParams.get('lessonId')
const lessonParam = '?lessonId=' + lessonId;
prStUrl = prStUrl + lessonParam;
abStUrl = abStUrl + lessonParam;

$(document).ready(function () {

  let presentTable = $('#presentDataTable').DataTable({
    ajax: {
      url: prStUrl, /*Data source*/
      dataSrc: '', /*object that holds the data*/
    },
    info: false,
    "bLengthChange": false,
    "oLanguage": {
      "sSearch": "<span class=\"fa fa-fw fa-search\" aria-hidden=\"true\"></span>"
    },
    pageLength: 10,
    "bAutoWidth": false,
    "aoColumns": [
      {
        sWidth: '70px',
        data: 'lastName'
      },
      {
        sWidth: '70px',
        data: 'firstName'
      },
      {
        sWidth: '30px',
        defaultContent: " <button type=\"button\" class=\"btn btn-secondary\">\n" +
            "            <i class=\"fa fa-arrow-right\" aria-hidden=\"true\" style='vertical-align: top'></i>\n" +
            "          </button>"
      }
    ],
    initComplete: function (settings, json) {
      setLessonAttendanceInfo();
    }
  });

  let absentTable = $('#absentDataTable').DataTable({
    ajax: {
      url: abStUrl, /*Data source*/
      dataSrc: '', /*object that holds the data*/
    },
    info: false,
    "bLengthChange": false,
    "oLanguage": {
      "sSearch": "<i class=\"fa fa-fw fa-search\" aria-hidden=\"true\"></i>"
    },
    pageLength: 10,
    "bAutoWidth": false,
    "aoColumns": [
      {
        sWidth: '30px',
        defaultContent: " <button type=\"button\" class=\"btn btn-secondary\">\n" +
            "            <i class=\"fa fa-arrow-left\" aria-hidden=\"true\" style='vertical-align: top'></i>\n" +
            "          </button>"
      },
      {
        sWidth: '100px',
        data: 'lastName'
      },
      {
        sWidth: '100px',
        data: 'firstName'
      }
    ],
    initComplete: function (settings, json) {
      setLessonAttendanceInfo();
    }
  });

  const presentTableBody = $('#presentDataTable tbody');
  const absentTableBody = $('#absentDataTable tbody');

  presentTableBody.add(absentTableBody).on('click', 'tr', function () {
    let studentPr = presentTable.row(this).data();
    let studentAb = absentTable.row(this).data();
    if (studentPr !== undefined) {
      updateStudentInfo(studentPr.id, lessonId)
    } else {
      updateStudentInfo(studentAb.id, lessonId)
    }
  });

  presentTableBody.on('click', 'button', function () {
    let studentId = presentTable.row($(this).parents('tr')).data().id;
    updateRegistered(studentId, 0)
  });

  absentTableBody.on('click', 'button', function () {
    let studentId = absentTable.row($(this).parents('tr')).data().id;
    updateRegistered(studentId, 1)
  });

  function updateStudentInfo(studentId, lessonId) {
    let baseUrl = /*[[@{/registration/student}]]*/ 'registration/student';
    let fullUrl = baseUrl + '?studentId=' + studentId + '&lessonId=' + lessonId;
    $('#studentInfo').load(fullUrl);
  }

  function updateRegistered(studentId, registered) {
    let request = new XMLHttpRequest();
    let url = 'registration/student/register' + lessonParam + '&studentId=' + studentId + "&registered=" + registered;
    request.open('GET', url);
    request.send(null);
    request.onreadystatechange = function () {
      refreshData();
    };
  }

  function refreshData() {
    presentTable.ajax.reload(function () {
      setLessonAttendanceInfo()
    }, false);

    absentTable.ajax.reload(function () {
      setLessonAttendanceInfo()
    }, false);
  }

  function setLessonAttendanceInfo() {
    const presentAmount = document.getElementById('studentsPresentNo');
    const absentAmount = document.getElementById('studentsAbsentNo');
    const totalAmount = document.getElementById('studentsTotalNo');
    presentAmount.innerText = presentTable.data().count();
    absentAmount.innerText = absentTable.data().count();
    totalAmount.innerText = presentTable.data().count() + absentTable.data().count();
  }
});