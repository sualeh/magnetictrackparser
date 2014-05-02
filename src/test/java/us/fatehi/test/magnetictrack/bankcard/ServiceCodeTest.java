/*
 *
 * Magnetic Track Parser
 * https://github.com/sualeh/magnetictrackparser
 * Copyright (c) 2014, Sualeh Fatehi.
 *
 * This library is free software; you can redistribute it and/or modify it under the terms
 * of the GNU Lesser General Public License as published by the Free Software Foundation;
 * either version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along with this
 * library; if not, write to the Free Software Foundation, Inc., 59 Temple Place, Suite 330,
 * Boston, MA 02111-1307, USA.
 *
 */
package us.fatehi.test.magnetictrack.bankcard;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import us.fatehi.magnetictrack.bankcard.ServiceCode;
import us.fatehi.magnetictrack.bankcard.ServiceCode1;
import us.fatehi.magnetictrack.bankcard.ServiceCode2;
import us.fatehi.magnetictrack.bankcard.ServiceCode3;

public class ServiceCodeTest
{

  @Test
  public void serviceCode_1()
  {
    final String rawServiceCode = null;
    final ServiceCode serviceCode = new ServiceCode(rawServiceCode);
    assertEquals(rawServiceCode, serviceCode.getRawTrackData());
    assertTrue("Should not have service code", !serviceCode.hasServiceCode());
  }

  @Test
  public void serviceCode_2()
  {
    final String rawServiceCode = "\t\t";
    final ServiceCode serviceCode = new ServiceCode(rawServiceCode);
    assertEquals(rawServiceCode, serviceCode.getRawTrackData());
    assertTrue("Should not have service code", !serviceCode.hasServiceCode());
  }

  @Test
  public void serviceCode_3()
  {
    final String rawServiceCode = "AQW";
    final ServiceCode serviceCode = new ServiceCode(rawServiceCode);
    assertEquals(rawServiceCode, serviceCode.getRawTrackData());
    assertTrue("Should not have service code", !serviceCode.hasServiceCode());
  }

  @Test
  public void serviceCode1()
  {
    final String rawServiceCode = "101";
    final ServiceCode serviceCode = new ServiceCode(rawServiceCode);
    assertEquals(rawServiceCode, serviceCode.getRawTrackData());
    assertTrue("Should have service code", serviceCode.hasServiceCode());
    assertEquals(ServiceCode1.v_1, serviceCode.getServiceCode1());
    assertEquals(ServiceCode2.v_0, serviceCode.getServiceCode2());
    assertEquals(ServiceCode3.v_1, serviceCode.getServiceCode3());
  }

  @Test
  public void serviceCode2()
  {
    final String rawServiceCode = "222 ";
    final ServiceCode serviceCode = new ServiceCode(rawServiceCode);
    assertEquals(rawServiceCode, serviceCode.getRawTrackData());
    assertTrue("Should have service code", serviceCode.hasServiceCode());
    assertEquals(ServiceCode1.v_2, serviceCode.getServiceCode1());
    assertEquals(ServiceCode2.v_2, serviceCode.getServiceCode2());
    assertEquals(ServiceCode3.v_2, serviceCode.getServiceCode3());
  }

  @Test
  public void serviceCode3()
  {
    final String rawServiceCode = "52525";
    final ServiceCode serviceCode = new ServiceCode(rawServiceCode);
    assertEquals(rawServiceCode, serviceCode.getRawTrackData());
    assertTrue("Should have service code", serviceCode.hasServiceCode());
    assertEquals(ServiceCode1.v_5, serviceCode.getServiceCode1());
    assertEquals(ServiceCode2.v_2, serviceCode.getServiceCode2());
    assertEquals(ServiceCode3.v_5, serviceCode.getServiceCode3());
  }

}
