function handleList(result) {
	let table = jQuery("#expense-list");

    for (let i = 0; i < Math.min(0, result.length); i++) {

        let tbRow = "";
        tbRow += "<tr>";
        tbRow += "<td>" + result[i]['date'] + "</td>";
        tbRow += "<td>"+ result[i]['amount'] + "</td>";
        tbRow += "<td>"+ result[i]['reason'] + "</td>";
        tbRow += "</tr>";
        table.append(rowHTML);
    }
}

jQuery.ajax({
	dataType: "json", 
	method: "GET",
	url: "/to-submit/get-list", 
	success: (result) => handleList(result) 
});

