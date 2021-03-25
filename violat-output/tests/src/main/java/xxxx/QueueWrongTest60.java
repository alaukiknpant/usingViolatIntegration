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
    id = "1,\\s*1",
    expect = Expect.ACCEPTABLE,
    desc = "atomic")
@State
public class QueueWrongTest60 {
    QueueWrong obj = new QueueWrong();
    
    @Actor
    public void actor0(StringResult2 result) {
        String r;
        
        try { obj.put(1); }
        catch (Exception e) { }
        
        try { obj.put(1); }
        catch (Exception e) { }
        
        try { r = ResultAdapter.get(obj.get()); }
        catch (Exception e) { r = ResultAdapter.get(e); }
        result.r1 = r;
        
        try { obj.put(1); }
        catch (Exception e) { }
    }
    
    @Actor
    public void actor1(StringResult2 result) {
        String r;
        
        try { obj.put(1); }
        catch (Exception e) { }
        
        try { r = ResultAdapter.get(obj.get()); }
        catch (Exception e) { r = ResultAdapter.get(e); }
        result.r2 = r;
    }
}