<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Customer Report</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 30px;
            background: #f5f7fa;
        }
        .report-container {
            background: #fff;
            padding: 20px 30px;
            border-radius: 12px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
            max-width: 600px;
            margin: auto;
        }
        h1 {
            text-align: center;
            color: #333;
            margin-bottom: 5px;
        }
        .subtitle {
            text-align: center;
            font-size: 14px;
            color: #666;
            margin-bottom: 20px;
        }
        hr {
            border: none;
            border-top: 1px solid #ddd;
            margin: 20px 0;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 10px;
        }
        th, td {
            text-align: left;
            padding: 10px;
            border-bottom: 1px solid #eee;
        }
        th {
            width: 40%;
            background: #f9f9f9;
        }
        .random-text {
            font-size: 14px;
            color: #555;
            margin: 15px 0;
            line-height: 1.5;
            text-align: justify;
        }
    </style>
</head>
<body>
<div class="report-container">
    <h1>Customer Report</h1>
    <p class="subtitle">Generated on ${currDate}</p>
    <hr>

    <table>
        <tr>
            <th>Customer ID</th>
            <td>${customerId}</td>
        </tr>
        <tr>
            <th>First Name</th>
            <td>${firstName}</td>
        </tr>
        <tr>
            <th>Last Name</th>
            <td>${lastName}</td>
        </tr>
    </table>

    <hr>
    <p class="random-text">
        This report has been generated for record-keeping purposes.
        It contains limited customer details for verification only.
        Please note that sensitive information is intentionally omitted.
    </p>

    <p class="random-text">
        The report format includes decorative sections and spacing
        to maintain consistency with standard documentation style.
        Further details can be added as per business requirements.
    </p>

    <hr>
    <p style="text-align:center; font-size:13px; color:#999;">
        --- End of Report ---
    </p>
</div>
</body>
</html>
