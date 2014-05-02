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

import us.fatehi.magnetictrack.bankcard.Name;

public class NameTest
{

  @Test
  public void name_1()
  {
    final String rawName = null;
    final Name name = new Name(rawName);
    assertEquals(rawName, name.getRawTrackData());
    assertTrue("Should not have name", !name.hasName());
  }

  @Test
  public void name_2()
  {
    final String rawName = "\t\t";
    final Name name = new Name(rawName);
    assertEquals(rawName, name.getRawTrackData());
    assertTrue("Should not have name", !name.hasName());
  }

  @Test
  public void name_3()
  {
    final String rawName = " /                        ";
    final Name name = new Name(rawName);
    assertEquals(rawName, name.getRawTrackData());
    assertTrue("Should not have name", !name.hasName());
  }

  @Test
  public void name1()
  {
    final String rawName = "SUALEH";
    final Name name = new Name(rawName);
    assertTrue("Should have name", name.hasName());
    assertEquals("SUALEH", name.getRawTrackData());
    assertEquals("", name.getFirstName());
    assertEquals("Sualeh", name.getLastName());
    assertEquals("Sualeh", name.getFullName());
  }

  @Test
  public void name2()
  {
    final String rawName = "FATEHI/SUALEH";
    final Name name = new Name(rawName);
    assertTrue("Does not have name", name.hasName());
    assertEquals(rawName, name.getRawTrackData());
    assertEquals("Sualeh", name.getFirstName());
    assertEquals("Fatehi", name.getLastName());
    assertEquals("Sualeh Fatehi", name.getFullName());
  }

  @Test
  public void name3()
  {
    final String rawName = "FATEHI/SUALEH             ";
    final Name name = new Name(rawName);
    assertTrue("Does not have name", name.hasName());
    assertEquals(rawName, name.getRawTrackData());
    assertEquals("Sualeh", name.getFirstName());
    assertEquals("Fatehi", name.getLastName());
    assertEquals("Sualeh Fatehi", name.getFullName());
  }

  @Test
  public void name4()
  {
    final String rawName = "MICHAELS OPEN VALUE CARD  ";
    final Name name = new Name(rawName);
    assertTrue("Should have name", name.hasName());
    assertEquals(rawName, name.getRawTrackData());
    assertEquals("", name.getFirstName());
    assertEquals("Michaels Open Value Card", name.getLastName());
    assertEquals("Michaels Open Value Card", name.getFullName());
  }

}
