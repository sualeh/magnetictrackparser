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
 * Service code, position 3 values.
 */
public enum ServiceCode3
  implements ServiceCodeType
{

  unknown(-1, "Unknown", ""),
  v_0(0, "No restrictions", "PIN required"),
  v_1(1, "No restrictions", ""),
  v_2(2, "Goods and services only", ""),
  v_3(3, "ATM only", "PIN required"),
  v_4(4, "Cash only", ""),
  v_5(5, "Goods and services only", "PIN required"),
  v_6(6, "No restrictions", "Prompt for PIN if PED present"),
  v_7(7, "Goods and services only", "Prompt for PIN if PED present"), ;

  private final int value;
  private final String allowedServices;
  private final String pinRequirements;

  private ServiceCode3(final int value,
                       final String allowedServices,
                       final String pinRequirements)
  {
    this.value = value;
    this.allowedServices = allowedServices;
    this.pinRequirements = pinRequirements;
  }

  /**
   * Gets the allowed services.
   * 
   * @return Allowed services.
   */
  public String getAllowedServices()
  {
    return allowedServices;
  }

  /**
   * @see ServiceCodeType#getDescription()
   */
  @Override
  public String getDescription()
  {
    return String.format("Allowed Services: %s. PIN Requirements: %s.",
                         allowedServices,
                         pinRequirements);
  }

  /**
   * Gets the the PIN requirements.
   * 
   * @return PIN requirements.
   */
  public String getPinRequirements()
  {
    return pinRequirements;
  }

  /**
   * @see ServiceCodeType#getValue()
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
