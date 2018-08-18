# rss-news

java client that downloads RSS page content and stores it in Cassandra. 
Store each item in a Cassandra table with a scheme that provides the following functionality

DB table: news

DB queries that can be executed:

	SELECT * FROM news WHERE url = ‘https://www.yahoo.com/news/rss’; 
	SELECT * FROM news WHERE url = ‘https://www.yahoo.com/news/rss’ AND date  = ‘2018-01-01’;

