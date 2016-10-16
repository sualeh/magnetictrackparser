/*
 *
 * Magnetic Track Parser
 * https://github.com/sualeh/magnetictrackparser
 * Copyright (c) 2014-2016, Sualeh Fatehi.
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
package us.fatehi.magnetictrack;


import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.trimToEmpty;

/**
 * Prints version information.
 */
public class Version
{

  private static final String implementationTitle;
  private static final String implementationVersion;
  private static final String implementationVendor;

  static
  {
    final Package pkg = Version.class.getPackage();
    implementationTitle = trimToEmpty(pkg.getImplementationTitle());
    implementationVersion = trimToEmpty(pkg.getImplementationVersion());
    implementationVendor = trimToEmpty(pkg.getImplementationVendor());
  }

  public static String getImplementationtitle()
  {
    return implementationTitle;
  }

  public static String getImplementationvendor()
  {
    return implementationVendor;
  }

  public static String getImplementationversion()
  {
    return implementationVersion;
  }

  public static void main(final String[] arguments)
  {
    if (!isBlank(implementationTitle) && !isBlank(implementationVersion))
    {
      System.out
        .println(String.format("%s, v%s%n%s",
                               implementationTitle,
                               implementationVersion,
                               implementationVendor));
    }
  }

  private Version()
  {
  }

}
