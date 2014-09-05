# javac -help
# Usage: javac <options> <source files>
# where possible options include:
#   -g                         Generate all debugging info
#   -nowarn                    Generate no warnings
#   -verbose                   Output messages about what the compiler is doing
#   -deprecation               Output source locations where deprecated APIs are used
#   -classpath <path>          Specify where to find user class files and annotation processors
#   -cp <path>                 Specify where to find user class files and annotation processors
#   -sourcepath <path>         Specify where to find input source files
#   -processor <class1>[,<class2>,<class3>...] Names of the annotation processors to run; bypasses default discovery process
#   -d <directory>             Specify where to place generated class files
#   -encoding <encoding>       Specify character encoding used by source files
#   -Werror                    Terminate compilation if warnings occur
#   @<filename>                Read options and filenames from file

# il carattere \ (backslash) indica che l'istruzione continua nella riga successiva.

rm -r ./../classes/*

javac \
  -sourcepath ./../src/ \
  -classpath ./../classes/ \
  -d ./../classes/ \
  ./../src/ama/ortasMgr/Main.java
