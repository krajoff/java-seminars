FROM bellsoft/liberica-openjdk-alpine:11.0.10 as BuildProject

COPY ./java ./src
RUN mkdir ./out
RUN javac -sourcepath ./src -d out src/ru/balancing/sample/Main.java
CMD java -classpath ./out ru.balancing.sample.Main

FROM scratch as OutputFiles
COPY --from=BuildProject /out /bin1
