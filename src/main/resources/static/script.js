function toggleVisibility(id) {
            var element = document.getElementById(id);
            element.style.display = (element.style.display === "none") ? "block" : "none";
        }

        function showInputBox(parentName, isFile) {
            let inputBox = document.getElementById('newItemInput');
            inputBox.style.display = 'block';
            inputBox.dataset.parentName = parentName;
            inputBox.dataset.isFile = isFile;
        }

        function hideInputBox(event) {
            let inputBox = document.getElementById('newItemInput');
            if (!inputBox.contains(event.target)) {
                inputBox.style.display = 'none';
            }
        }

        function addNewItem() {
            let inputBox = document.getElementById('newItemInput');
            let parentName = inputBox.dataset.parentName;
            let isFile = inputBox.dataset.isFile === "true";
            let newItemName = document.getElementById('newItemName').value;

            if (newItemName.trim() === "") {
                inputBox.style.display = 'none';
                return;
            }

            fetch('/add', {
                method: 'POST',
                headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                body: `parentName=${parentName}&newItemName=${newItemName}&isFile=${isFile}`
            }).then(() => {
                location.reload(); // Refresh page to reflect changes
            });
        }

        document.addEventListener('click', hideInputBox);