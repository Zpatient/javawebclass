<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <meta charset="UTF-8" />
    <meta
            name="viewport"
            content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"
    />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Jodit Test Document</title>
    <link rel="stylesheet" href="./jodit/app.css" />
    <link rel="stylesheet" href="./jodit/build/jodit.min.css" />
    <script src="./jodit/build/jodit.js"></script>
    <link rel="icon" href="https://xdsoft.net/jodit/pro/favicon.png"/>
</head>
<body>
<form action="/test/a" method="post">
    <style>
        #box {
            padding: 100px;
            margin: 20px;
            position: relative;
            height: 500px;
        }
        @media (max-width: 480px) {
            #box {
                padding: 0;
            }
        }
    </style>
    <div id="box">
			<textarea id="editor">
				&lt;img src="https://xdsoft.net/jodit/files/artio.jpg"/&gt;
			</textarea>
    </div>
    <script>
        const editor = Jodit.make('#editor' ,{
            uploader: {
                url: 'https://xdsoft.net/jodit/finder/?action=fileUpload'
            },
            filebrowser: {
                ajax: {
                    url: 'https://xdsoft.net/jodit/finder/'
                }
            }
        });
    </script>
</form>
</body>
</html>