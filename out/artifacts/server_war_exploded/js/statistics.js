google.load("visualization", "1", {packages: ["corechart"]});
google.setOnLoadCallback(drawChart);

function drawChart() {
    var attr = document.getElementById("tmi_s").childNodes;
    var attr2 = document.getElementById("cfi_s").childNodes;
    var arr = [], arr2 = [];
    arr.push(["tmi", 'time']);
    arr2.push(["cfi", 'cost']);
    for (var i = 0; i < attr.length; i++) {
        item = attr.item(i);
        item2 = attr2.item(i);
        id = item.id;
        id2 = item2.id;
        text = item.textContent;
        text2 = item2.textContent;
        if (id !== "" && id !== undefined && !!text && text !== "") {
            arr.push([id, +text]);
        }
        if (id2 !== "" && id2 !== undefined && !!text2 && text2 !== "") {
            arr2.push([id2, +text2]);
        }
    }
    var data = google.visualization.arrayToDataTable(/*[
            ['Газ', 'Объём'],
            ['Азот',     78.09],
            ['Кислород', 20.95],
            ['Аргон',    0.93],
            ['Углекислый газ', 0.03]
        ]*/arr), data2 = google.visualization.arrayToDataTable(arr2);
    var options = {
        title: 'Расходованное время',
        is3D: false,
        pieResidueSliceLabel: 'Остальное'
    };
    var options2 = {
        title: 'Расходованные средства',
        is3D: false,
        pieResidueSliceLabel: 'Остальное'
    };
    var chart = new google.visualization.PieChart(document.getElementById('tmi-label'));
    var chart2 = new google.visualization.PieChart(document.getElementById('cfi-label'));
    chart.draw(data, options);
    chart2.draw(data2, options2);
    chart2.width = 500;
}

function tmi_cfi(id, id2) {
    if (document.getElementById(id).style.display === 'none'){
        document.getElementById(id).style.display = 'block';
        document.getElementById(id2).style.display = 'none';
    }
}