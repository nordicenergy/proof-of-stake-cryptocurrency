if exist jre (
    set javaDir=jre\bin\
)

%javaDir%java.exe -Xmx1024m -cp "classes;lib/*;conf" -Dpwrc.runtime.mode=desktop pwrc.tools.SignTransactionJSON
