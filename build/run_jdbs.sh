java \
    -agentlib:jdwp=transport=dt_socket,address=localhost:8000,server=y \
    -classpath ./../classes/:./../lib/postgresql-9.3-1102.jdbc41.jar \
    ama.ortasMgr.Main
