package xxxx;

import java.util.Arrays;
import java.util.AbstractMap;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import queue.QueueWrong;
import org.openjdk.jcstress.annotations.*;
import org.openjdk.jcstress.infra.results.*;

@JCStressTest
@Outcome(
    id = "",
    expect = Expect.ACCEPTABLE,
    desc = "atomic")
@State
public class QueueWrongTest8 {
    QueueWrong obj = new QueueWrong();
    
    @Actor
    public void actor0(StringResult0 result) {
        String r;
        
        try { obj.put(0); }
        catch (Exception e) { }
        
        try { obj.put(0); }
        catch (Exception e) { }
    }
    
    @Actor
    public void actor1(StringResult0 result) {
        String r;
        
        try { obj.put(0); }
        catch (Exception e) { }
        
        try { obj.put(0); }
        catch (Exception e) { }
    }
}
