package com.bpfaas.common.utils.identify;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author rjf
 * @since 2021/1/26
 */
public class IdentityGeneratorTest {

    @Test
    public void generateId() {
        for (int i = 0; i < 5; i++) {
            final String id = IdentityGenerator.nextId();
            Assert.assertNotNull(id);
            Assert.assertEquals(32, id.length());
            System.out.println(id);
        }
    }

}