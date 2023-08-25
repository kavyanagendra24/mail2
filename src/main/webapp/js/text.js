 function addColumn() {
            var table = document.getElementById("usersTable");
            var headerRow = table.rows[0];
            var newColumnIndex = headerRow.cells.length;

            // Update the header row
            var newHeaderCell = document.createElement("th");
            newHeaderCell.textContent = "New Column";
            headerRow.appendChild(newHeaderCell);

            // Update each data row
            var dataRows = table.rows;
            for (var i = 1; i < dataRows.length; i++) {
                var newRowCell = dataRows[i].insertCell(newColumnIndex);
                newRowCell.textContent = "New Data";
            }
        }