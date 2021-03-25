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
    id = "java.lang.Exception,\\s*java.lang.Exception,\\s*java.lang.Exception,\\s*java.lang.Exception,\\s*java.lang.Exception,\\s*java.lang.Exception",
    expect = Expect.ACCEPTABLE,
    desc = "atomic")
@State
public class QueueWrongTest94 {
    QueueWrong obj = new QueueWrong();
    
    @Actor
    public void actor0(StringResult6 result) {
        String r;
        
        try { r = ResultAdapter.get(obj.get()); }
        catch (Exception e) { r = ResultAdapter.get(e); }
        result.r1 = r;
        
        try { r = ResultAdapter.get(obj.get()); }
        catch (Exception e) { r = ResultAdapter.get(e); }
        result.r2 = r;
        
        try { r = ResultAdapter.get(obj.get()); }
        catch (Exception e) { r = ResultAdapter.get(e); }
        result.r3 = r;
    }
    
    @Actor
    public void actor1(StringResult6 result) {
        String r;
        
        try { r = ResultAdapter.get(obj.get()); }
        catch (Exception e) { r = ResultAdapter.get(e); }
        result.r4 = r;
        
        try { r = ResultAdapter.get(obj.get()); }
        catch (Exception e) { r = ResultAdapter.get(e); }
        result.r5 = r;
        
        try { r = ResultAdapter.get(obj.get()); }
        catch (Exception e) { r = ResultAdapter.get(e); }
        result.r6 = r;
    }
}