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
 * Service code, position 2 values.
 */
public enum ServiceCode2
  implements ServiceCodeType
{

  unknown(-1, "Unknown"),
  v_0(0, "Normal"),
  v_2(2, "By issuer"),
  v_4(4, "By issuer unless explicit bilateral agreeement applies"), ;

  private final int value;
  private final String authorizationProcessing;

  private ServiceCode2(final int value, final String authorizationProcessing)
  {
    this.value = value;
    this.authorizationProcessing = authorizationProcessing;
  }

  /**
   * Gets the authorization processing rules.
   * 
   * @return Authorization processing rules.
   */
  public String getAuthorizationProcessing()
  {
    return authorizationProcessing;
  }

  /**
   * @see us.fatehi.magnetictrack.bankcard.ServiceCodeType#getDescription()
   */
  @Override
  public String getDescription()
  {
    return String.format("Authorization Processing: %s.",
                         authorizationProcessing);
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
