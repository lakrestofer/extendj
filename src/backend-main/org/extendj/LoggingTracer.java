package org.extendj;

// import org.extendj.ast.ASTState;
import org.extendj.ast.ASTNode;
import org.extendj.ast.ASTState;


import java.util.Map;
import java.util.HashMap;

import java.io.BufferedWriter;
import java.io.IOException;

public class LoggingTracer implements ASTState.Trace.Receiver {

    private BufferedWriter out;
    private long startTime;

    public LoggingTracer(BufferedWriter out) {
        this.out = out;
        this.startTime = System.nanoTime();
    }

    public void accept(ASTState.Trace.Event event, ASTNode node, String attribute, Object params, Object value) {
        try {
            // out.write(String.format("%s in %s at %d ms.\n", event.toString(), node.toString(), System.currentTimeMillis() - startTime));
            out.write(String.format("%s\t%s\t%d\n", event.toString(), ASTNode.nodeToString(node), System.nanoTime() - startTime));
        } catch(IOException e) {
            // e.printStackTrace();
            System.err.println("uh oh");
        }
    }
}
