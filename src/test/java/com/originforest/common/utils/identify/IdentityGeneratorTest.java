package com.originforest.common.utils.identify;

import com.originforest.common.utils.IdentityUtils;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author rjf
 * @since 2021/1/26
 */
public class IdentityGeneratorTest {

    @Test
    public void generateId() {
        for (int i = 0; i < 10; i++) {
            final String id = IdentityUtils.nextId();
            Assert.assertNotNull(id);
            Assert.assertEquals(IdentityUtils.ID_LENGTH, id.length());
            Assert.assertTrue(IdentityUtils.isValid(id));
            System.out.println(id);
        }
    }
}