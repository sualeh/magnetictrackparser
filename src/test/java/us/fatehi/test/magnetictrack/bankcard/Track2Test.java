/*
 *
 * Magnetic Stripe Parser
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

import org.junit.Test;

import us.fatehi.magnetictrack.bankcard.CardBrand;
import us.fatehi.magnetictrack.bankcard.MajorIndustryIdentifier;
import us.fatehi.magnetictrack.bankcard.ServiceCode1;
import us.fatehi.magnetictrack.bankcard.ServiceCode2;
import us.fatehi.magnetictrack.bankcard.ServiceCode3;
import us.fatehi.magnetictrack.bankcard.Track2;

public class Track2Test {

	@Test
	public void track2__1() {
		final String track2Data = ";5266092201416174=16042010000056700100?";
		final Track2 track2 = new Track2(track2Data);
		checkCardData(track2);
	}

	@Test
	public void track2__2() {
		final String track2Data = "%B5266092201416174^FATEHI/SUALEH^16042010000000000000000000000000000567001000?;5266092201416174=16042010000056700100?";
		final Track2 track2 = new Track2(track2Data);
		checkCardData(track2);
	}

	private void checkCardData(final Track2 track2) {
		assertEquals(
				";5266092201416174=16042010000056700100?",
				track2.getTrackData());
		assertEquals("5266092201416174", track2.getPrimaryAccountNumber()
				.getAccountNumber());
		assertEquals(CardBrand.MasterCard, track2.getPrimaryAccountNumber().getCardBrand());
		assertEquals(MajorIndustryIdentifier.mii_5, track2.getPrimaryAccountNumber().getMajorIndustryIdentifier());
		assertEquals("2016-04", track2.getExpirationDate().toString());
		assertEquals("201", track2.getServiceCode().toString());
		assertEquals(ServiceCode1.v_2, track2.getServiceCode()
				.getServiceCode1());
		assertEquals(ServiceCode2.v_0, track2.getServiceCode()
				.getServiceCode2());
		assertEquals(ServiceCode3.v_1, track2.getServiceCode()
				.getServiceCode3());
		assertEquals("0000056700100",
				track2.getDiscretionaryData());
	}

}
