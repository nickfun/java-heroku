# Standard execution:
#     foreman start
# Scale things up:
#     foreman start -c web_a=3,web_b=0

web_a: java -cp target/classes:target/dependency/* MyServer
web_b: java -cp target/classes:target/dependency/* MyServer

