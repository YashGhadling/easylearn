<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <style>
        body {
            font-family: 'Georgia', serif;
            background-color: #f0f0f0;
        }
        .certificate-container {
            width: 750px;
            margin: auto;
            background: #fff;
            border: 10px solid #d4af37;
            padding: 20px;
            text-align: center;
        }
        h1 {
            font-size: 2.5em;
            color: #333;
        }
        .subtitle {
            font-size: 1.2em;
            color: #555;
            font-style: italic;
        }
        .name {
            font-size: 2em;
            font-weight: bold;
            color: #1e90ff;
        }
        .course-name {
            font-size: 1.6em;
            font-weight: bold;
            color: #d4af37;
        }
        .details {
            font-size: 1.2em;
            color: #333;
        }
        .signature-section {
            margin-top: 30px;
            text-align: center;
        }
        .signature-line {
            width: 150px;
            height: 1px;
            background-color: #333;
            margin: 5px auto;
        }
    </style>
</head>
<body>
    <div class="certificate-container">
        <h1>Certificate of Completion</h1>
        <p class="subtitle">This certifies that</p>
        <h2 class="name"><%= request.getAttribute("userName") %></h2>
        <p class="details">has successfully completed the</p>
        <h3 class="course-name"><%= request.getAttribute("courseName") %></h3>
        <p class="details">on</p>
        <p class="details"><%= new java.text.SimpleDateFormat("MMMM dd, yyyy").format(new java.util.Date()) %></p>
        <p class="details"><strong>Score Achieved:</strong> <%= request.getAttribute("score") %></p>
        <div class="signature-section">
            <p>Authorized Signature</p>
            <div class="signature-line"></div>
        </div>
    </div>
</body>
</html>