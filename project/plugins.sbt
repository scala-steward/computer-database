addSbtPlugin("org.scalameta"    % "sbt-scalafmt"        % "2.3.1")
addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % "1.6.1")
addSbtPlugin("org.scoverage"    % "sbt-scoverage"       % "1.6.1")
addSbtPlugin("com.eed3si9n"     % "sbt-buildinfo"       % "0.9.0")
addSbtPlugin("com.typesafe.sbt" % "sbt-git"             % "1.0.0")
addSbtPlugin("org.wartremover"  % "sbt-wartremover"     % "2.4.3")
addSbtPlugin("io.spray"         % "sbt-revolver"        % "0.9.1")

libraryDependencies += "com.spotify" % "docker-client" % "8.16.0"
