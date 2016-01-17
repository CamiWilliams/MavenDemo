<html>
	<body>
		<h2>Hello World!</h2>
		<!--<form action="LoginServlet" method="POST">
			<input type="submit" value="Login">
		</form> -->
		
		<h1>URL upload</h1>
		<form method="GET" action="upload.html" commandName="handleURL">
			<input type="submit" value="Submit">
		</form>
		
		<h1>File upload</h1>
		<form method="POST" action="upload.html" commandName="handleFileUpload" enctype="multipart/form-data">
			<input type="file" name="pic" accept="image/*">
			<input type="submit" value="Submit">
		</form>
				
		{$message}
	</body>
</html>
