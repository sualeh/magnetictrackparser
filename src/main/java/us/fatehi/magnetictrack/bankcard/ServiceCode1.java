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
package us.fatehi.magnetictrack.bankcard;


/**
 * Service code, position 1 values.
 */
public enum ServiceCode1
  implements ServiceCodeType
{

  unknown(-1, "Unknown", ""),
  v_1(1, "International interchange", ""),
  v_2(2, "International interchange", "Integrated circuit card"),
  v_5(5, "National interchange", ""),
  v_6(6, "National interchange", "Integrated circuit card"),
  v_7(7, "Private", ""),
  v_9(9, "Test", ""), ;

  private final int value;
  private final String interchange;
  private final String technology;

  private ServiceCode1(final int value,
                       final String interchange,
                       final String technology)
  {
    this.value = value;
    this.interchange = interchange;
    this.technology = technology;
  }

  /**
   * @see us.fatehi.magnetictrack.bankcard.ServiceCodeType#getDescription()
   */
  @Override
  public String getDescription()
  {
    return String.format("Interchange: %s. Technology: %s.",
                         interchange,
                         technology);
  }

  /**
   * Gets the interchange rules.
   * 
   * @return Interchange rules.
   */
  public String getInterchange()
  {
    return interchange;
  }

  /**
   * Gets the the technology.
   * 
   * @return Technology.
   */
  public String getTechnology()
  {
    return technology;
  }

  /**
   * @see us.fatehi.magnetictrack.bankcard.ServiceCodeType#getValue()
   */
  @Override
  public int getValue()
  {
    return value;
  }

  /**
   * @see Object#toString()
   */
  @Override
  public String toString()
  {
    return String.format("%d - %s", value, getDescription());
  }

}
