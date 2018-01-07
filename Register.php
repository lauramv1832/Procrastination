<?php
    $con = mysqli_connect("localhost", "id4198481_saad23", "Soccer1q2w3e", "id4198481_users");
    
    $name = $_POST["name"];
    $username = $_POST["username"];
    $password = $_POST["password"];

    $statement = mysqli_prepare($con, "INSERT INTO user (name, username, password) VALUES (?, ?, ?)");
    mysqli_stmt_bind_param($statement, "siss", $name, $username, $password);
    mysqli_stmt_execute($statement);
    
    $response = array();
    $response["success"] = true;  
    
    echo json_encode($response);
?>
