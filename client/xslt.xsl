<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : newstylesheet.xsl
    Created on : February 24, 2019, 7:55 PM
    Author     : hd
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html"/>

    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template match="/">
        <html>
            <head>
                <title>Chat Message</title>
                <style>                    
                    .container {
                    border: 2px solid #dedede;
                    background-color: #f1f1f1;
                    border-radius: 5px;
                    padding: 20px;
                    margin: 10px 0;
                    }
                    .darker {
                    border-color: #ccc;
                    background-color: #ddd;
                    }
                    .container::after {
                    content: "";
                    clear: both;
                    display: table;
                    }
                    .time-right {
                    float: right;
                    color: #aaa;
                    }

                    .time-left {
                    float: left;
                    color: #999;
                    }
                </style>
            </head> 
            <body>
                <xsl:for-each select="chatSession/message">
                    <div class="container darker">
                        <h style="font-size: 150%">                        
                            <b>
                                <xsl:value-of select="sender"/>
                            </b>
                        </h>
                        <p  style="background-color : #c3c3c3;
                            color : black;
                            font-size: 150%;
                            font-family: verdana">
                            <xsl:value-of select="body"/>
                        </p>
                        <span class="time-left">
                            <xsl:value-of select="timestamp"/>
                        </span>
                    </div>
                </xsl:for-each>
                
            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>