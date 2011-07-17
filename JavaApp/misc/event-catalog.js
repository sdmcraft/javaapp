var debug = true;
var xmlHttp = new XMLHttpRequest();
function getEventCatalog() {	
	xmlHttp.onreadystatechange = function() {
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {

			if (debug) {
				alert("Got response from service");
			}
			var domdata = xmlHttp.responseXML;
			var html = "<table>";
			var eventList = domdata.getElementsByTagName("event");
			alert("eventlist size=" + eventList.length);
			for (i = 0; i < eventList.length; i++) {
				alert("i=" + i);
				var eventName = eventList[i].getElementsByTagName("name")[0];
				alert("eventname=" + eventName);
				var description = eventList[i]
						.getElementsByTagName("description")[0];
				var path = eventList[i].getElementsByTagName("path")[0];
				var begin = eventList[i].getElementsByTagName("date-begin")[0];
				var end = eventList[i].getElementsByTagName("date-end")[0];
				var duration = eventList[i].getElementsByTagName("duration")[0];
				var eventInfo = eventList[i].getElementsByTagName("event-info")[0];
				html += "<tr>";
				html += "<td>";
				html += "<a href='http://www.w3schools.com'  target='_blank'>";
				html += "<u><b>";
				html += "<font color='blue'>";
				html += eventName.childNodes[0].nodeValue;
				html += "</font>";
				html += "</u></b>";
				html += "</a>";
				html += "</td>";
				html += "</tr>";
				html += "<td>";
				html += "<b>";

				html += "<font color='green'>";
				html += begin.childNodes[0].nodeValue.substring(0, 9)
						+ "&nbsp; TO &nbsp;"
						+ end.childNodes[0].nodeValue.substring(0, 9);
				html += "</font>";
				html += "</b>";
				html += "</td>";
				html += "<tr>";
				html += "</tr>";
				html += "<tr>";
				html += "<td>";
				html += "<i>";
				html += description.childNodes[0].nodeValue;
				html += "</i>";
				html += "</td>";
				html += "<td>";
				html += "<image src='http://ec2-50-18-128-218.us-west-1.compute.amazonaws.com/flash-training/event/event_listing.gif' />";
				html += "</td>";
				html += "</tr>";
				html += "<tr>";
				html += "<td>";
				html += "</td>";
				html += "</tr>";
				html += "<tr>";
				html += "<td>";
				html += "</td>";
				html += "</tr>";
			}
			html += "</table>";
			document.getElementById('content_div').innerHTML = html;
		}
	};
	xmlHttp.open("GET", "/api/xml?action=event-list", true);
	xmlHttp.send();

};