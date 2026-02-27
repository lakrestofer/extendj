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
        accept(event, "", node, attribute, params, value);
    }

    public void accept(ASTState.Trace.Event event, String aspect, ASTNode node, String attribute, Object params, Object value) {
        try {
            long timestamp = System.nanoTime() - startTime;
            int astNodeId = System.identityHashCode(node);
            String astNodeName = ASTNode.nodeToString(node);
            String eventName = event.toString();
            out.write(String.format("%d\t%s\t%d\t%s\t%s\t%s\n", timestamp, aspect, astNodeId,  astNodeName , attribute, eventName));
        } catch(IOException e) {
            // e.printStackTrace();
            System.err.println("uh oh");
        }
    }
}
