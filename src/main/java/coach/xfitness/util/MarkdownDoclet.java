package coach.xfitness.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.util.ElementFilter;

import com.sun.source.doctree.DocCommentTree;
import com.sun.source.util.DocTrees;

import jdk.javadoc.doclet.Doclet;
import jdk.javadoc.doclet.DocletEnvironment;
import jdk.javadoc.doclet.Reporter;

public class MarkdownDoclet implements Doclet {
    Reporter reporter;

    @Override
    public void init(Locale locale, Reporter reporter) {

    }

    @Override
    public String getName() {
        return "MarkdownDoclet";
    }

    @Override
    public Set<? extends Option> getSupportedOptions() {
        Option[] options = {
                new Option() {
                    private final List<String> options = Arrays.asList(
                            "--markdown");

                    @Override
                    public int getArgumentCount() {
                        return 0;
                    }

                    @Override
                    public String getDescription() {
                        return "generate as markdown";
                    }

                    @Override
                    public Kind getKind() {
                        return Option.Kind.STANDARD;
                    }

                    @Override
                    public List<String> getNames() {
                        return options;
                    }

                    @Override
                    public String getParameters() {
                        return "none";
                    }

                    @Override
                    public boolean process(String option, List<String> arguments) {
                        return true;
                    }

                }
        };

        return new HashSet<>(Arrays.asList(options));
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latest();
    }

    @Override
    public boolean run(DocletEnvironment docEnv) {
        DocTrees docTrees = docEnv.getDocTrees();

        ElementFilter.typesIn(docEnv.getIncludedElements()).stream()
                .sorted((a, b) -> {
                    int byPackageName = a.getEnclosingElement().getSimpleName().toString()
                            .compareTo(b.getEnclosingElement().getSimpleName().toString());

                    if (byPackageName == 0) {
                        return a.getSimpleName().toString()
                                .compareTo(b.getSimpleName().toString());
                    }

                    return byPackageName;
                })
                .forEach(t -> {
                    System.out.println("\n## " + t.getSimpleName() + "\n");

                    Map<String, StringBuilder> kindMap = new HashMap<>();

                    t.getEnclosedElements().forEach(e -> {
                        String kind = e.getKind().toString();
                        StringBuilder row;

                        if (kindMap.containsKey(kind)) {
                            row = kindMap.get(kind);
                        } else {
                            row = new StringBuilder();
                        }

                        row.append(getElementRow(docTrees, e));

                        kindMap.put(kind, row);
                    });

                    Arrays.asList(
                            "FIELD",
                            "CONSTRUCTOR",
                            "METHOD")
                            .forEach(kind -> {
                                System.out.println("| " + kind + " | DESCRIPTION |");
                                System.out.println("| --- | --- |");

                                StringBuilder row = kindMap.get(kind);
                                System.out.println(row == null ? "" : row);
                            });

                });

        return true;
    }

    private StringBuilder getElementRow(DocTrees docTrees, Element e) {
        String name = e.toString()
                .replaceAll("\\w+\\.", "");
        String type = e.asType().toString()
                .replaceAll("^\\(.*\\)", "")
                .replaceAll("\\w+\\.", "");
        String fullBody = "";

        DocCommentTree docCommentTree = docTrees.getDocCommentTree(e);
        if (docCommentTree != null) {
            fullBody = docCommentTree.getFullBody().toString().replace("\n", "");//.replace("\n", "<br/>");
        }

        return new StringBuilder()
                .append("| ").append(name)
                .append(": ").append(type)
                .append(" | ").append(fullBody)
                .append(" |\n");
    }
}